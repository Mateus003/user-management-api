package com.mateus.trip_planner_api.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "destinations")
public class DestinationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "trip_destination",
            joinColumns = @JoinColumn(name = "trip_id"),
            inverseJoinColumns = @JoinColumn(name = "destination_id")
    )
    private Set<TripModel> trips = new HashSet<>();

}
