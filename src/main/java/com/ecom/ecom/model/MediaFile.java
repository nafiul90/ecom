package com.ecom.ecom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@NoArgsConstructor
@ToString
@Entity

public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "File type is mandatory")
    @Size(min = 3,max = 10,message = "File type should be between 3 and 10 character long")
    private String fileType;  // image or video

    @NotBlank(message = "url is mandatory")
    //@Min(value = 5,message = "url should be at least 5 character long")
    private String url;       // the file path only

    public MediaFile(String fileType, String url) {
        this.fileType = fileType;
        this.url = url;
    }

}
