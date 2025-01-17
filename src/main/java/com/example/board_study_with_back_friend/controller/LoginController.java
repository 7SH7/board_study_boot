package com.example.board_study_with_back_friend.controller;

import com.example.board_study_with_back_friend.service.MemberService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 로그인 실험(FE 코드 없을 때!) https://developers.google.com/oauthplayground/
@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Value("${google.oauth.client-id}")
    private String clientId;

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/google")
    public ResponseEntity<Map<String, Object>> googleLogin(@RequestParam String credential) {

        // 밑의 코드는 GoogleIdTokenVerifier 내부 구성을 보여주면서 설명하기
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(/*여러분의 clientId를 넣어주세요.*/))
                .build();

        try {
            // ID Token이 같은지 유효성 검사 :: https://jwt.io/ 에서 확인 가능
            GoogleIdToken idToken = verifier.verify(credential);
            if (idToken != null) {

                // Payload는 ID Token에 포함된 사용자 정보를 나타냄.(GoogleIdToken에 ID Token이 포함되어 있음.)
                // -> 여기에서 사용자 정보를 확인하고 저장
                Payload payload = idToken.getPayload();

                String userId = payload.getSubject();
                String email = payload.getEmail();
                String name = (String) payload.get("name");

                // 사용자 정보 확인 또는 저장
                memberService.saveOrUpdateUser(userId, email, name);

                // 응답 데이터 생성
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("email", email);
                response.put("name", name);
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid ID token."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Server error occurred."));
        }
    }
}
