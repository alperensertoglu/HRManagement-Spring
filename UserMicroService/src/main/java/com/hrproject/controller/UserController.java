package com.hrproject.controller;

import com.hrproject.constant.EndPoints;
import com.hrproject.dto.request.*;
import com.hrproject.repository.entity.Avanstelebi;
import com.hrproject.repository.entity.Izintelebi;
import com.hrproject.repository.entity.UserProfile;
import com.hrproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.hrproject.constant.EndPoints.LOGIN;
import static com.hrproject.constant.EndPoints.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping(LOGIN)
    public ResponseEntity<String> dologin(@RequestBody UserLoginDto dto) {

        return ResponseEntity.ok(userService.logindto(dto));
    }


    @PostMapping("/addAnnualPermission ")
    public ResponseEntity<String> addAnnualPermission(@RequestBody UserLoginDto dto, @RequestParam int days) {

        return ResponseEntity.ok(userService.requestAnnualLeave(dto, days));
    }

    @PostMapping("/addAParentalPermission ")
    public ResponseEntity<String> addAParentalPermission(@RequestBody UserLoginDto dto, @RequestParam int days) {

        return ResponseEntity.ok(userService.requestParentalLeave(dto, days));
    }

    @GetMapping("/getTotalAnnualLeave/{username}")
    public ResponseEntity<Integer> getPermission(@RequestBody UserLoginDto dto) {

        return ResponseEntity.ok(userService.getTotalAnnualLeave(dto));
    }
    //@GetMapping("/findallbyadmin")
    // public ResponseEntity<List<UserProfile>> findallbyadmin(String tokken){
    //    return ResponseEntity.ok(userService.finduserprofilesbyadmin(tokken));
    //  }

//    @GetMapping("/allEmployees")
//    public ResponseEntity<List<UserProfile>> getAllEmployees() {
//
////        List<UserProfile> employeeList = userService.getAllEmployees();
//
//        return ResponseEntity.ok(employeeList);
//    }

    @GetMapping(EndPoints.FIND_BY_ID + "/{authId}")
    public ResponseEntity<UserProfile> findByAuthId(@PathVariable Long authId) {
        return ResponseEntity.ok(userService.findEmployeeByAuthId(authId));
    }

    @GetMapping("/allEmployees/{companyId}")
    public ResponseEntity<List<UserProfile>> getAllEmployees(@PathVariable Long companyId) {


        return ResponseEntity.ok(userService.getAllEmployees(companyId));
    }


    @PostMapping("/addemployee/{id}")
    public ResponseEntity<String> addEmployee(@PathVariable Long id, AddEmployeeDto dto){

        userService.addEmployee(id,dto);

        return ResponseEntity.ok("Kayıt oldu");
    }

    @PostMapping("/findallbyadmin")
    public ResponseEntity<List<UserProfile>> findallbyadmin(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {

        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }

        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.finduserprofilesbyadmin(token));
    }

    @PostMapping("/findallbyadminpending")
    public ResponseEntity<List<UserProfile>> findallbyadminpending(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {

        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }

        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.finduserprofilesbyadminpending(token));
    }

    @PostMapping("/activationbyadmin")
    public void activasyonbyadmin(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        ;
        if (!authorId.equals(null)) {
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
                System.out.println(token);

            }else if (requestBody != null && requestBody.containsKey("token")) {
                System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
                String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
                token = tokenWithoutBearer;
                System.out.println(token);
            }
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.activitosyon(token, longSayi);


        }


    }

    @PostMapping("/finduserbyadmin")
    public ResponseEntity<UserProfile> findallbyuseradmin(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        if (authorization.startsWith("Bearer ")) {
            String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
            System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
            // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
            // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
            token = tokenWithoutBearer;
        }else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        }
        System.out.println(token);
        return ResponseEntity.ok(userService.userProfilefindbidwithtokken(token));

    }


    @PutMapping("/update1" + "/{Id}")
    public ResponseEntity<UserProfile> updateById(@PathVariable Long Id, @RequestBody UserProfileUpdateRequestDto dto) {

        return ResponseEntity.ok(userService.updateprofile(Id,dto));
    }

    @PostMapping("/findallguestbycompanymanager")
    public ResponseEntity<List<UserProfile>> findallguestbycompanymanager(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {

        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }

        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.findallguestbycompanymanager(token));
    }@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/izinal")
    public ResponseEntity<Boolean> izintalebi( @RequestParam(required = false) String token,
                                               @RequestHeader(required = false) String authorization,
                                               @RequestBody(required = false) Map<String, String> requestBody,
                                               @RequestParam String sebep,@RequestParam String izinTur,
                                               @RequestParam String tarihler) throws ParseException {
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
        System.out.println(tarihler);
        System.out.println("icerdyi<");
        System.out.println(token);
        String tokenWithoutBearer=token;





        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.izintelebi(tokenWithoutBearer,sebep,tarihler,izinTur));
    }
    @PostMapping("/findallrequesbycompanymanager")
    public ResponseEntity<List<Izintelebi>> findallrequestbycompanymanager(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {

        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }

        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.findallreguestbycompanymanager(token));
    }
    @PostMapping("/deletebyadmin")
    public void deletebyadmin(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
            System.out.println("burdayim");
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.deletebyadmin(token, longSayi);


        }
    @PostMapping("/activedate")
    public void activedate(
            @RequestParam Integer p,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(p);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
        System.out.println("burdayim");


        userService.activedate(token, p);


    }



    @PostMapping("/deleterequestbycompanymanager")
    public void deleterequestbycompanymanager(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
            System.out.println("burdayim");
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.deleterequestbyadmin(token, longSayi);


        }



    @PostMapping("/activerequestbycompanymanager")
    public void activerequestbycompanymanager(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
            System.out.println("burdayim");
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.activerequestbyadmin(token, longSayi);


        }



    @PostMapping("/findalloldrequesbycompanymanager")
    public ResponseEntity<List<Izintelebi>> findalloldrequesbycompanymanager(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {

        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }

        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.findalloldreguestbycompanymanager(token));
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<Boolean> addEmployee(@RequestBody AddEmployeeCompanyDto dto,@RequestParam(required = false) String token,
                                               @RequestHeader(required = false) String authorization,
                                               @RequestBody(required = false) Map<String, String> requestBody) throws ParseException {
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
        System.out.println("burdayim");
        return ResponseEntity.ok(userService.addEmployee(token,dto));
    }
    @PutMapping("/updateemployee")
    public ResponseEntity<UserProfile> updateUserProfile( UserProfileUpdateDto dto){
        return ResponseEntity.ok(userService.updateprofileManager(dto));
    }
    @PostMapping("/deleteprofilebycompanymanager")
    public void deleteprofilebycompanymanager(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
            System.out.println("burdayim");
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.deleteprofilebycompanymanager(token, longSayi);


        }



    @PostMapping("/addsalary")
    public ResponseEntity<Boolean> addsalary(
            @RequestParam Integer authorId,@RequestParam Integer maas,String name,String surname,@RequestParam Integer companyid
    ) {
        System.out.println(authorId);
        String token = null;

        System.out.println("burdayim");
        Long longSayi = (long) authorId; // int'i Long'a dönüştür
        Long companyid1= (long) companyid;
        userService.maasekle(longSayi,maas,name,surname,companyid1);


        return ResponseEntity.ok(true);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/avansal")
    public ResponseEntity<Boolean> avanstalebi( @RequestParam(required = false) String token,
                                               @RequestHeader(required = false) String authorization,
                                               @RequestBody(required = false) Map<String, String> requestBody,
                                               @RequestParam String sebep,@RequestParam Integer miktar
                                               ) throws ParseException {

        System.out.println("icerdyi<");
        System.out.println(token);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }




        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.avanstelebi(token,sebep,miktar));
    }

    @PostMapping("/findallavansrequesbycompanymanager")
    public ResponseEntity<List<Avanstelebi>> findallavansrequestbycompanymanager(
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {

        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }

        // Alınan token ile işlemlerinizi gerçekleştirin
        return ResponseEntity.ok(userService.findallavansreguestbycompanymanager(token));
    }

    @PostMapping("/deleteavansrequestbycompanymanager")
    public void deleteavansrequestbycompanymanager(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
            System.out.println("burdayim");
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.deleteavansrequestbyadmin(token, longSayi);


        }



    @PostMapping("/activeavansrequestbycompanymanager")
    public void activeavansrequestbycompanymanager(
            @RequestParam Integer authorId,
            @RequestParam(required = false) String token,
            @RequestHeader(required = false) String authorization,
            @RequestBody(required = false) Map<String, String> requestBody) {
        System.out.println(authorId);
        if (token != null) {
            System.out.println("Sorgu parametresinden gelen token: " + token);
            // Sorgu parametresinden gelen token'ı işleyebilirsiniz
            token = token.substring(7);
        } else if (authorization != null) {
            // İstek başlığından gelen token'ı Bearer prefix'ini ayırarak işleyebilirsiniz
            if (authorization.startsWith("Bearer ")) {
                String tokenWithoutBearer = authorization.substring(7); // 7, "Bearer " prefix uzunluğudur
                System.out.println("İstek başlığından gelen token: " + tokenWithoutBearer);
                // tokenWithoutBearer artık Bearer prefix'inden arındırılmış token'ı içerir
                // tokenWithoutBearer değişkenini kullanarak işlemlerinizi gerçekleştirebilirsiniz
                token = tokenWithoutBearer;
            }
        } else if (requestBody != null && requestBody.containsKey("token")) {
            System.out.println("İstek gövdesinden gelen token: " + requestBody.get("token"));
            String tokenWithoutBearer = requestBody.get("token").substring(7); // 7, "Bearer " prefix uzunluğudur
            token = tokenWithoutBearer;
            System.out.println(token);
        } else {
            System.out.println("Token sağlanmadı");
        }
            System.out.println("burdayim");
            Long longSayi = (long) authorId; // int'i Long'a dönüştür

            userService.activeavansrequestbyadmin(token, longSayi);


        }


    }

