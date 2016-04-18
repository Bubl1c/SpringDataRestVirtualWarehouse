package com.amozh;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@RepositoryRestController
@RequestMapping(/*Api.CONTEXT + "/" + */"test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final @NonNull TestRepository testRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public TestOuter save(@RequestBody TestOuter entity) {
        testRepository.save(entity);
        return entity;
    }
}
