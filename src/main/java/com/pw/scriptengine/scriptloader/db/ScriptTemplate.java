package com.pw.scriptengine.scriptloader.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "script_template")
//@IdClass(ScriptTemplate.IdClass.class)
public class ScriptTemplate {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "script_order")
    private Integer scriptOrder;

    @Column(name = "script_name")
    private String scriptName;

    @Column(name = "script_body")
    private String scriptBody;

    @Column(name = "description")
    private String description;

//    @Data
//    static class IdClass implements Serializable {
//        private String processName;
//    }
}