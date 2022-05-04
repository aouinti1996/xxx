package tn.esprit.TRAVELGO.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.TRAVELGO.config.WebSocketEventListener;
import tn.esprit.TRAVELGO.service.MessageService;
import tn.esprit.TRAVELGO.service.UserService;

@RestController
@RequestMapping("/ws/users")
public class UserWSController {
    @Autowired
    WebSocketEventListener webSocketEventListener;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    /*@GetMapping("/{currentUserId}")
    public ResponseEntity<List<OnlineUserDto>> getOnlineUsers(@PathVariable Long currentUserId) {
        List<OnlineUserDto>usersWithStatus = new ArrayList<>();

        List<OnlineUserDto>offlineUsers = MapperUtils.mapperList(userService.getAllUsers(),OnlineUserDto.class);
        offlineUsers.stream().map(u->{
            u.setStatus("OFFLINE");
            return u;
        }).collect(Collectors.toList());

        try{
            Set<OnlineUserDto>onlsSet = webSocketEventListener.getOnlineUsrs();
            if(onlsSet!=null){
                List<OnlineUserDto>onls = onlsSet.stream().collect(Collectors.toList());
                onls.forEach(o->{
                    int count = messageService.countNewMessagesFromOnlineUser(currentUserId, o.getUserId());
                    o.setNoOfNewMessages(count);
                    o.setStatus("ONLINE");
                });
                usersWithStatus.addAll(onls);
                List<OnlineUserDto> finalOnls = onls;
                offlineUsers.forEach(u->{
                    if(finalOnls.stream().map(OnlineUserDto::getUsername).collect(Collectors.toList()).contains(u.getUsername())==false){
                        usersWithStatus.add(u);
                    }
                });
            }
            else{
                usersWithStatus.addAll(offlineUsers);
            }

        }
        catch(Exception ex){
            
        }
        return ResponseEntity.ok(usersWithStatus);
    }*/
}

