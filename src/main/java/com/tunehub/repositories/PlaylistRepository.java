package com.tunehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tunehub.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist,Integer> {

}
