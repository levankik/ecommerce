package com.softgen.softlab.ecommercebackend.api.controller.user;

import com.softgen.softlab.ecommercebackend.model.dao.AddressDAO;
import com.softgen.softlab.ecommercebackend.api.model.DataChange;
import com.softgen.softlab.ecommercebackend.model.Address;
import com.softgen.softlab.ecommercebackend.model.LocalUser;
import com.softgen.softlab.ecommercebackend.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Getter
@Setter
@RequiredArgsConstructor
public class UserController {

    private final AddressDAO addressDAO;

    private SimpMessagingTemplate simpMessageTemplate;

    private final UserService userService;

    @GetMapping("{userId}/address")
    public ResponseEntity<List<Address>> getAddress(
            @AuthenticationPrincipal LocalUser user, @PathVariable Long userId) {
        if (!userService.userHasPermissionToUser(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(addressDAO.findByUser_Id(userId));
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<Address> putAddress(
            @AuthenticationPrincipal LocalUser user, @PathVariable Long userId,
            @RequestBody Address address) {
                if (!userService.userHasPermissionToUser(user, userId)) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
                address.setId(null);
                LocalUser refUser = new LocalUser();
                refUser.setId(userId);
                address.setUser(refUser);
                Address saveAddress = addressDAO.save(address);
                simpMessageTemplate.convertAndSend("/topic/user/" + userId + "/address",
                        new DataChange<>(DataChange.ChangeType.INSERT, address));
                return ResponseEntity.ok(saveAddress);
                }

    @PatchMapping("/{userId}/address/{addressId}")
    public ResponseEntity<Address> patchAddress (
            @AuthenticationPrincipal LocalUser user, @PathVariable Long userId,
            @PathVariable Long addressId, @RequestBody Address address) {
        if (!userService.userHasPermissionToUser(user, userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (address.getId() == addressId) {
            Optional<Address> opOriginalAddress = addressDAO.findById(addressId);
            if (opOriginalAddress.isPresent()) {
                LocalUser originalUser = opOriginalAddress.get().getUser();
                if (originalUser.getId() == userId) {
                    address.setUser(originalUser);
                    Address saveAddress = addressDAO.save(address);
                    simpMessageTemplate.convertAndSend("/topic/user/" + userId + "/address",
                            new DataChange<>(DataChange.ChangeType.UPDATE, address));
                    return ResponseEntity.ok(saveAddress);
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

//
//
//    private boolean userHasPermission(LocalUser user, Long id) {
//        return user.getId() == id;
//    }
}
