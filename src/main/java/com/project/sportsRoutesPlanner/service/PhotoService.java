package com.project.sportsRoutesPlanner.service;

import com.project.sportsRoutesPlanner.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("IPhotoService")
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;
}
