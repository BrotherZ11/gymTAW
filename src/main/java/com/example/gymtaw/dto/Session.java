package com.example.gymtaw.dto;
//David Zarzavilla Borrego 80%
//David Molina Lopez 20%
import com.example.gymtaw.entity.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class Session {
    private Integer id = -1;
    private String name;
    private Integer trainerId;
    private Set<Integer> exerciseHasSessions = new LinkedHashSet<>();
    private Set<Integer> routineHasSessions = new LinkedHashSet<>();
    private Set<Integer> types =  new LinkedHashSet<>();

}
