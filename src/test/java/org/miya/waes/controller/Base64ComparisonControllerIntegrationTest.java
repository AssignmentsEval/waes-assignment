package org.miya.waes.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.miya.waes.WaesAssignmentApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.miya.waes.util.StaticMessages.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration Tests, to compare two strings in base64 data format via http requests
 *
 * @author Yasin Kızılkaya
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WaesAssignmentApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class Base64ComparisonControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCreateBase64ComparisionWithEmptyRightSideExpectNotFound() throws Exception {
        long comparisionId = 123L;
        String leftSide = "YmFzaWM=";

        // update leftSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + leftSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':null}"));

        // get diff
        mvc.perform(get("/v1/diff/" + comparisionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{ 'errorMessage': '" + EMPTY_SIDE + "'}"));
    }

    @Test
    public void testCreateBase64ComparisionWithSidesHaveDifferentSizeExpectMisMatchResponse() throws Exception {
        long comparisionId = 123L;
        String leftSide = "YmFzaWM=";
        String rightSide = "YmFza=";

        // update leftSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + leftSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':null}"));

        // update rightSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/right")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + rightSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':'" + rightSide + "'}"));

        // get diff
        mvc.perform(get("/v1/diff/" + comparisionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'isEqual': false, 'message': '" + MISMATCH_SIZES + "'}"));
    }

    @Test
    public void testCreateBase64ComparisionWithSameDataExpectMatchMessage() throws Exception {
        long comparisionId = 123L;
        String leftSide = "YmFzaWM=";
        String rightSide = "YmFzaWM=";

        // update leftSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + leftSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':null}"));

        // update rightSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/right")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + rightSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':'" + rightSide + "'}"));

        // get diff
        mvc.perform(get("/v1/diff/" + comparisionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'isEqual': true, 'message': '" + MATCHED + "'}"));
    }

    @Test
    public void testCreateBase64ComparisionWithDifferentSidesExpectMisMatchResponseWithOffsets() throws Exception {
        long comparisionId = 123L;
        String leftSide = "YxFKaWM=";
        String rightSide = "YmFzaWM=";

        // update leftSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + leftSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':null}"));

        // update rightSide
        mvc.perform(post("/v1/diff/" + comparisionId + "/right")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + rightSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':'" + rightSide + "'}"));

        // get diff
        mvc.perform(get("/v1/diff/" + comparisionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'isEqual': false, 'message': '" + MISMATCHED + "', 'mismatchOffsets': [1,3]}"));
    }

    @Test
    public void testGetDiffOfUnavailableComparision() throws Exception {
        long comparisionId = 123L;

        mvc.perform(get("/v1/diff/" + comparisionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{ 'errorMessage': '" + ID_NOT_AVAILABLE + "'}"));
    }
}
