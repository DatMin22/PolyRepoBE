//package com.PolyRepo.PolyRepo.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//@Service
//public class FacebookService {
//
//    @Value("${EAATkwn9uJLoBOyYstvzL9vb11EZA4opiiJAOenhkvyZBZCQZCTAvk7MIo0xaz692wgRN2y60wtJ8pGvBeOmusCxkZBviFjGzc0mQaf4yJQQF4BZBTPqBZBgmA8GUvJru3zGnnvh9PBiX7WkV8R3R0Uh7AHPEJrlmHiMteZBreuE3A4XSF02HpV6nXqD0ERiMdUjTZCnbTZBdqkg6XEAjp9k2tXGudCzH8u5eb1opGLAuP493x3CtACpiwf5RqdzAZDZD}")
//    private String accessToken; // Thay thế bằng access token của ứng dụng Facebook của bạn
//
//    public void shareOnFacebook(String postId, String message, String link) {
//        String apiUrl = "https://graph.facebook.com/v13.0/" + postId + "/sharedposts";
//
//        String requestBody = "message=" + message + "&link=" + link + "&access_token=" + accessToken;
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForObject(apiUrl, requestBody, String.class);
//    }
//}
