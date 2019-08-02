package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TrackController {

    private TrackService trackService;
    ResponseEntity responseEntity;;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){

        try{
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("Successfully created", HttpStatus.CREATED);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }
      return  responseEntity;

    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){

        try{
            responseEntity = new ResponseEntity<Track>(trackService.getById(id),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTracks(){

        try{
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable int id){

        try{
            responseEntity = new ResponseEntity<Optional<Track>>(trackService.deleteTrackById(id),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

        return responseEntity;
    }

    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody int id,@RequestBody Track track){

        try{

            responseEntity = new ResponseEntity(trackService.updateTrack(id,track),HttpStatus.OK);
        }
        catch (Exception e){
            responseEntity = new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

        return responseEntity;
    }



}
