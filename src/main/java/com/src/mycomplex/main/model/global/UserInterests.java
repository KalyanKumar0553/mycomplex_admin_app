package com.src.mycomplex.main.model.global;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "user_interests")
@Data
public class UserInterests {

	@EmbeddedId
    private UserInterestID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userID")
    private UserInfo user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("interestID")
    private Interest interest;
    
    private Date lastModifiedOn;
    
    private Date lastCreatedOn;
    
    private Long createdBy;
    
    private Long modifiedBy;
}
