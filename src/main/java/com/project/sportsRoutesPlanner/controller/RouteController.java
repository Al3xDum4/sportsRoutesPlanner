package com.project.sportsRoutesPlanner.controller;

import com.project.sportsRoutesPlanner.model.DifficultyLevel;
import com.project.sportsRoutesPlanner.model.Event;
import com.project.sportsRoutesPlanner.model.Route;
import com.project.sportsRoutesPlanner.model.RouteCategory;
import com.project.sportsRoutesPlanner.service.RouteService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("allroutes")
    public String showAllRoutes(Model model) {
        List<Route> routeList = routeService.findAll();
        model.addAttribute("routes", routeList);
        return "route/showallroutes";
    }

    @GetMapping("allhikingroutes")
    public String showAllHikingRoutes(Model model) {
        List<Route> hikings = routeService.findHikings();
        model.addAttribute("hikingroutes", hikings);
        model.addAttribute("byRouteName", Comparator.comparing(Route::getRouteName));
        return "route/showallhikingroutes";
    }

    @GetMapping("/addhikingroute")
    public String addRoute(Model model) {
        List<DifficultyLevel> difficultyLevels = routeService.allDifficultLevels();
        model.addAttribute("models", new Route());
        model.addAttribute("diflevels", difficultyLevels);
        return "route/addroute";
    }

    @PostMapping("/addhikingroute")
    public String addHikingRoute(@ModelAttribute Route route, String routeName, String description, Double distance,
                                 Double maxAltitude, Double duration, String difficultyLevel,
                                 @RequestParam("routeBackground") Blob file) throws IOException {

        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        route.setImage(file);

        route.setRouteName(routeName);
        route.setDescription(description);
        route.setDistance(distance);
        route.setMaxAltitude(maxAltitude);
        route.setDuration(duration);
        route.setRouteCategory(RouteCategory.HIKING);
        route.setDifficultyLevel(DifficultyLevel.valueOf(difficultyLevel));

        Route savedRoute = routeService.save(route);

//        String uploadDir = "./route-background/" + savedRoute.getRouteId();
//        Path uploadPath = Paths.get(uploadDir);
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        try (InputStream inputStream = multipartFile.getInputStream()) {
//            Path filePath = uploadPath.resolve(fileName);
//            System.out.println(filePath.toFile().getAbsolutePath());
//            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new IOException("Could not save uploaded file: " + fileName);
//        }

        return "redirect:/allhikingroutes";
    }

//    @GetMapping("/route/image/{id}")
//    public void showRouteImage(@PathVariable Integer id,
//                               HttpServletResponse response) throws IOException {
//        response.setContentType("image/jpeg"); // Or whatever format you wanna use
//
//        Route route = routeService.findById(id);
//
//        InputStream is = new ByteArrayInputStream(route.getImage());
//        IOUtils.copy(is, response.getOutputStream());
//    }

    @GetMapping("/route/image/{id}")
    public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Integer id) throws SQLException {

        Optional<Route> route = Optional.ofNullable(routeService.findById(id));
        byte[] imageBytes = null;
        if (route.isPresent()) {

            imageBytes = route.get().getImage().getBytes(1,
                    (int) route.get().getImage().length());
        }

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }

    @GetMapping("/edithikingroute/{id}")
    public String editHikingRoute(Model model, @PathVariable Integer id) {
        List<DifficultyLevel> difficultyLevels = routeService.allDifficultLevels();
        Route route = routeService.findById(id);
        model.addAttribute("route", route);
        model.addAttribute("diflevels", difficultyLevels);
        return "route/editroute";
    }

    @PostMapping("/edithikingroute/{id}")
    public String editHikingRoute(@ModelAttribute Route route, @PathVariable Integer id) {
        route.setRouteCategory(RouteCategory.HIKING);
        routeService.save(route);
        return "redirect:/allhikingroutes";
    }

    @GetMapping("/deletehikingroute/{id}")
    public String deleteHikingRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/allhikingroutes";
    }

    @GetMapping("/deletecyclingroute/{id}")
    public String deleteCyclingRoute(@PathVariable Integer id) {
        routeService.deleteById(id);
        return "redirect:/allhikingroutes";
    }

}
