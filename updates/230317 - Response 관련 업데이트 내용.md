# Response 관련 Class UML
![Untitled](https://github.com/TenQuest-Team/TenQuest-BE/blob/develop/updates/imgs/uml.png)

# ☑️ **도입 배경: 구현된 Dto는 Status, Code등의 추가정보를 담을 수 없다.**

- 기존 방식대로 `Dto`를 그대로 리턴하는 경우 요청한 데이터만 전달하기 때문에 클라이언트의 요청을 처리한 결과를 보낼 수 없다는 단점이 존재합니다.
- 처리결과(status)를 반영하기 위해 기존의 dto 데이터뿐만 아니라 status, status code를 담을 수 있는 `Response` 타입을 만들었고, 기존 `Dto` 클래스에서 쉽게 `Response` 타입으로 변환 가능하도록 `Responseable` 인터페이스를 구현하였습니다.

**********************기존의 `Dto`를 그대로 리턴하는 방식**********************

```java
{
        "memberId": "9ff94143a2084a779ce2e5fce0a3095b",
        "userId": "spongebob",
        "userInfo": null,
        "userName": "스폰지밥",
        "userEmail": "spongebob@gmail.com"
    }
```

**`Response` 타입으로 리턴하는 방식으로 변경**

```java
{
    "status": "OK",
    "code": 200,
    "data": {
        "memberId": "9ff94143a2084a779ce2e5fce0a3095b",
        "userId": "spongebob",
        "userInfo": null,
        "userName": "스폰지밥",
        "userEmail": "spongebob@gmail.com"
    }
}
```
  
---  
  

# ☑️ Responsable Interface

`Responsable` 인터페이스는 `Dto` 클래스들을 `Response` 객체로 변환해주기 위한 인터페이스로 변환 메소드가 구현되어 있어서 `Dto` 클래스에 *Implements* 해주는 것만으로도 변환 메소드(`toResponse(...)`)를 사용할 수 있게 됩니다.

****************************************Response Interface code****************************************

```java
package com.kns.tenquest.response;
import com.kns.tenquest.dto.DataTransferObject;
import com.kns.tenquest.dto.ResponseDto;
import com.kns.tenquest.response.Response;
import com.kns.tenquest.response.ResponseStatus;
@SuppressWarnings("unchecked")
public interface Responseable<E extends DataTransferObject>{
    /**
     * @author woody
     * @since JDK 1.8
     * @param responseStatus 클라이언트의 요청에 대한 처리 결과를 반영하기 위한 파라미터 입니다.
     *                       [예시]클라이언트의 요청이 정상적으로 처리된 경우
     *                          ResponseStatus.OK
     *
     *                       [예시]클라이언트가 찾고자하는 결과가 없어서 반환할 수 없는 경우
     *                          ResponseStatus.NOT_FOUND
     *
     *                       ** ResponseStatus class 참고
     * @return Response Object를 리턴합니다.
     */
    default Response<E> toResponse(ResponseStatus responseStatus){
        return new ResponseDto<E>(responseStatus, (E)this).toResponse();
    };
}
```

## 사용 예시

기존 `Dto` 클래스 다음과 같이 변경

1. `Responsable` 인터페이스 *implements* 하기
2. Controller에서 리턴 타입 수정

### 1. **`Responsable` 인터페이스 *implements* 하기**

`Responsable`의 **generic은 해당 Dto 클래스와 동일한 타입으로 지정해주셔야 합니다.**

[예시] `MemberDto`에서 *implements* 하려고 하는 경우 → public class MemberDto implements **Responseable\<MemberDto\>**

변경 전

```java
@Getter
@Setter
@NoArgsConstructor
public class MemberDto implements DataTransferObject<Member> {
    ...
}

```

변경 후

```java
@Getter
@Setter
@NoArgsConstructor
public class MemberDto implements DataTransferObject<Member>, Responseable<MemberDto> {
    ...
}

```

`Responseable` 인터페이스를 *implement* 하면 `toResponse(...)` 메서드를 바로 사용할 수 있습니다. 

### 2. Controller에서 리턴 타입 수정

컨트롤러에서 `Response` 타입을 리턴하도록 수정합니다.

**변경 전 `Dto`를 리턴하던 코드**

```java
@ResponseBody
@GetMapping("/members")
public MemberDto apiGetMemberByUserNameAndUserId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail)
{
        MemberDto memberDto = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;

          return memberDto;
    }
```

**위의 코드를 다음과 같이 `Response` 타입을 리턴하도록 변경**

```java
@ResponseBody
@GetMapping("/members")
public **Response<MemberDto>** apiGetMemberByUserNameAndUserId(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail)
{
        MemberDto memberDto = memberService.getMemberByUserNameAndEmail(userName,userEmail);
        ResponseStatus responseStatus = ResponseStatus.OK;
        if (nullableMemberDto.memberId == null) responseStatus = ResponseStatus.NOT_FOUND;

          return **memberDto.toResponse(responseStatus);**
    }
```

Service에서 받은 `Dto` 객체를 `Response`로 변환하여 리턴해줍니다.

별다른 구현없이 dto 인스턴스에서 `toResponse(...)` 메서드를 호출하는 것으로 쉽게 변환할 수 있습니다.
  
---  
  
# ☑️ DtoList

여러개의 `Dto`를 담기위해 기존에 사용하던 `List<Dto>` 방식에서 `Responseable`을 사용하기 위해 확장한 `DtoList` 클래스 입니다.

```java
/* imports ... */
@NoArgsConstructor
public class DtoList<E> extends ArrayList implements DataTransferObject, Responseable {
    public <T> DtoList (List<T> anyList){
        for (T elem : anyList){
        this.add(elem);
        }
    }

    @Override
    public Object toEntity() throws NoSuchAlgorithmException {
        return null;
    }

    @Override
    public DataTransferObject toDto(Object entity) {
        return null;
    }

    @Override
    public Response toResponse(ResponseStatus responseStatus) {
        return Responseable.super.toResponse(responseStatus);
    }

    @Override
    public Object[] toArray(IntFunction generator) {
        return super.toArray(generator);
    }

    @Override
    public Stream stream() {
        return super.stream();
    }

    @Override
    public Stream parallelStream() {
        return super.parallelStream();
    }
}
```

**`MemberService`에서 `List<>` 타입의 반환타입을 가지던 method들을 `DtoList<>` 타입으로 리턴하도록 변경**

```java
public DtoList<MemberDto> getAllMembers() {
        DtoList<MemberDto> memberDtoList = new DtoList<>(memberRepository.findAll());
        return memberDtoList;
    }
```

**`MemberController` 변경**  
```java
@ResponseBody
    @GetMapping("/get/members")
    public Response<MemberDto> apiGetAllMembers(){
        ResponseStatus responseStatus = ResponseStatus.OK;
        DtoList<MemberDto> memberDtoList = memberService.getAllMembers();

        return memberDtoList.toResponse(responseStatus);
    }
```
