package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);

        }catch(EmptyResultDataAccessException e){
            return "삭제에 실패하였습니다. 해당 ID는 DB에 없습니다.";
        }
        return "삭제되었습니다. ID : " +id;
    }
    //한페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault (size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(Pageable.unpaged());
        if(pagingUser.isLast()){

        }
        List<User> users = pagingUser.getContent();
        return users;
    }
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        //user/4를 찾으면 데이터베이스에서 못찾아오게 되면 user가 null이 될것 아냐?
        //그럼 return null이 리턴이 되자나.... 그럼 프로그램에 문제가 잇지 않겠니?
        // Optional로 너의 User객체를 감싸서 가져올테니 null 인지 아닌지 판단해서 Return해

        //람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자가 없습니다.");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다." + id);
            }
        });
        //아래와 같이 할 경우는 없는 값은 모두 null
//        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });
        //user객체 : 자바 Object
        //요청 : 웹브라우져
        //변환(웹브라우져가 이해할 수 잇는 데이터 -> json(Gson 라이브러리)
        //스프링부트 = MessageConverter 라는 애기 응답시에 자동 적용
        //만약에 자바 오브젝트를 리턴하게 되면 MessageConverter 가 jackson 라이브러리 호출해서
        //user 오브젝트를 json으로 변환 해서 브라우저에게 전달한다.
        return user;
    }

    @PostMapping("/dummy/join")
//    public String join(String username, String password, String email){
   public String join(User user){
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
