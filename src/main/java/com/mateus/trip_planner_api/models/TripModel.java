package com.mateus.trip_planner_api.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "trips")
public class TripModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column
    private BigDecimal cost;

    @ManyToMany
    @JoinTable(
            name = "user_trip",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserModel> users =  new HashSet<>();


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "trips",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<DestinationModel> destinations ;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "trip",  fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ActivityModel> activities = new HashSet<>();



}
