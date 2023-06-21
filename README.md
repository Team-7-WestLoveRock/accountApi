# accountApi
회원 정보를 제공하며, 회원의 상태(가입, 탈퇴, 휴면)를 관리합니다.

## 요구사항
- 인증 처리는 Gateway 서비스에 위임합니다.

- 회원 정보를 제공하며, 회원의 상태(가입, 탈퇴, 휴면)를 관리합니다.

## ERD

![accountapi-erd](https://github.com/Team-7-WestLoveRock/accountApi/assets/76582376/54ad9e91-f8bf-4b3a-8e0e-81a859ebcdba)
<br>

# API SPEC

## Account

**GET /account/api/accounts/{userId}**  
ex) 사용자 최소 정보 조회  
Response
```json
{
    "userId": "westloverock",
    "password": "8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92",
    "email": "westloverock@github.com"
}
```

**POST /account/api/accounts**  
ex) 사용자 등록  
Request
```json
{
    "userId": "westloverock",
    "password": "westloverockPassword",
    "name": "서정석",
    "nickname": null,
    "email": "westloverock@github.com",
    "phoneNumber": null
}
```
Response
```json
{
    "createdAt" : "2023-06-06"
}
```

**POST /account/api/accounts/{userId}**   
ex) 사용자의 상태 변경  
Request
```json
{
    "state": "휴면"
}
```
Response
```json
{
    "state": "휴면"
}
```
**PUT /account/api/accounts/{userId}**  
ex) 사용자의 정보 변경  
Request
```json
{
    "userId": "westloverock",
    "password": "westloverockPassword",
    "name": "서정석",
    "nickname": "서경석",
    "email": "westloverock@github.com",
    "phoneNumber": null
}
```
Response
```json
{
    "userId": "westloverock",
    "password": "westloverockPassword",
    "name": "서정석",
    "nickname": "서경석",
    "email": "westloverock@github.com",
    "phoneNumber": null
}
```

---

## Profile

**POST /account/api/accounts/{userId}/profile**     
ex) 사용자 프로필 정보 변경 (등록, 수정, 삭제)  

Request
```json
{
    "imagePath": "/src/static/useridProfileImage1"
}
```


## LoginLog
**GET /account/api/accounts/{userId}/loginLogs**  
ex) 사용자 로그인 기록 조회    
Response
```json
[
    {
        "loginDate": "2023-06-06T12:00:00",
        "ipAddress": "127.0.0.1"
    },
    {
        "loginDate": "2023-06-07T12:00:00",
        "ipAddress": "127.0.0.1"
    }
]
```

**GET /account/api/accounts/{userId}/loginLogs/last**  
ex) 사용자 로그인 마지막 기록 조회    
Response
```json
{
    "loginDate": "2023-06-06T12:00:00",
    "ipAddress": "127.0.0.1"
}
```

**POST /account/api/accounts/{userId}/loginLog**  
ex) 사용자 로그인 시간 기록   
Request
```json
{
    "ipAddress": "127.0.0.1"
}
```

Response
```json
{
    "loginDate": "2023-06-06T12:00:00"
}
```









