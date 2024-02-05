package com.tunehub.services;

import java.util.List;

import com.tunehub.entity.Playlist;

public interface PlaylistService {

	public void addPlaylist(Playlist playlist);

	public List<Playlist> fetchAllPlaylists();

}
