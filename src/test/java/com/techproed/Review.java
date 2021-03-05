package com.techproed;

public class Review {
    // Assertion icin kac farklı yontem vardır

    // Body-Matcher -- Hard Assert
    // JsonPath -- Nesne olusturduk, json uzerinden response a ulastık
    // GSON -- De-Serilization
    // POJO -- Class lar olusturduk
    // ObjectMapper - String verdim bana Map olusturdu


    // 2) Request Body - Patch,Post, Put  Expected Data -- Get ve Delete
    // ReqBody ve ExpectedData yi hangi sekillerde olusturduk
    // Data Collections
    // JSONObject
    // ObjectMapper
    // POJO

    // 3) TestBase class larımız
    // RequestSpecification-- Interface in den nesne urettik
    // RequestBuilder methodunu kullandık
    // build() metodunu --
    // Before Annotation unun kullandım-- JUnit teki Test metodunu kullandık
    // Test ten once specleri olusturmak icin Before u kullandık.

    // spec.patParams ("","");-- URl i olusturduk
    // query params varsa (/todos?esefagkj=skfds)
    // spec.pathparams().queryparams();

    // Hard Assertion-- assertEquals,assertTrue,assertFalse
    // Hard Assertion-- body-- Matchers--

    // Soft Assert --- assertEquals,assertTrue,assertFalse

    // Neleri assert ettik
    // status code, content type, datayı -- expected data ve response u assert ettik.






}
