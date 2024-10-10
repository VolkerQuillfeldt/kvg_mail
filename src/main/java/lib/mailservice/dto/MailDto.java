package lib.mailservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDto {

   private String body;
    private String subject;
    private String host;
}
