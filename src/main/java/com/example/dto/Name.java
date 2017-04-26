package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
   {"results":[ {"gender":"male",
                 "name":{"title":"mr","first":"connor","last":"taylor"},
                 "location":{"street":"8962 alexandra street","city":"invercargill","state":"waikato","postcode":21669},
                 "email":"connor.taylor@example.com",
                 "login":{"username":"brownostrich511","password":"atomic","salt":"ukJgWbSu","md5":"f43dcedbebc32b0bf984d4678d80c5f4","sha1":"b2b7ae3c60ed16240c74d34bce86d4618d6a04c4","sha256":"711c76e49798f25930044bf1bacc02129ed212439c6f13c33c8a7c267b96a43a"},
                 "dob":"1987-01-09 12:40:53",
                 "registered":"2008-04-19 12:49:25",
                 "phone":"(027)-202-4080","cell":"(289)-940-4692","id":{"name":"","value":null},
                 "picture":{"large":"https://randomuser.me/api/portraits/men/4.jpg",
                            "medium":"https://randomuser.me/api/portraits/med/men/4.jpg",
                            "thumbnail":"https://randomuser.me/api/portraits/thumb/men/4.jpg"}
                  ,"nat":"NZ"}],
                  "info":{"seed":"307c859ded74d581","results":1,"page":1,"version":"1.1"}}
*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Name {
	public String first;
	public String last;
}
