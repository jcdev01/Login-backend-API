package com.Jairo.API_Login_Page.exceptions;

import com.Jairo.API_Login_Page.dto.MessageReponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(ResoruceNotFoundExption.class)
public ResponseEntity<String> HandleResourceNotFound(ResoruceNotFoundExption e){
   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
}
   @ExceptionHandler(BadCredentialsException.class)
   public ResponseEntity<MessageReponseDTO> handleBadCredentials(BadCredentialsException e) {
      return ResponseEntity.status(401).body(new MessageReponseDTO("Email ou senha incorretos"));
   }

   @ExceptionHandler(DisabledException.class)
   public ResponseEntity<MessageReponseDTO> handleDisabled(DisabledException e) {
      return ResponseEntity.status(401).body(new MessageReponseDTO("Email ainda não confirmado, verifique sua caixa de entrada"));
   }

   @ExceptionHandler(RuntimeException.class)
   public ResponseEntity<MessageReponseDTO> handleRuntime(RuntimeException e) {
      return ResponseEntity.status(400).body(new MessageReponseDTO(e.getMessage()));
   }
}






