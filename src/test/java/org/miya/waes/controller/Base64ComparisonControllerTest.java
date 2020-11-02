package org.miya.waes.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.miya.waes.dto.DiffResponseDTO;
import org.miya.waes.entity.Base64Comparison;
import org.miya.waes.service.ComparisonService;
import org.miya.waes.service.DiffService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.miya.waes.util.StaticMessages.MATCHED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit Tests, for http endpoints in {@link org.miya.waes.controller.Base64ComparisonController} class
 *
 * @author Yasin Kızılkaya
 */

@RunWith(SpringRunner.class)
@WebMvcTest(Base64ComparisonController.class)
public class Base64ComparisonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ComparisonService comparisonService;

    @MockBean
    private DiffService diffService;

    @Test
    public void updateLeftSideWithoutValidDataExpectBadRequest() throws Exception {
        mvc.perform(post("/v1/diff/123/left")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateLeftSideWithValidDataExpectCreated() throws Exception {
        Long comparisionId = 123L;
        String leftSide = "YmFzaWM=";

        Base64Comparison comparision = new Base64Comparison(comparisionId);
        comparision.setLeftSide(leftSide);

        Mockito.when(comparisonService.updateLeftSide(comparisionId, leftSide))
                .thenReturn(comparision);

        mvc.perform(post("/v1/diff/" + comparisionId + "/left")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + leftSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':'" + leftSide + "', 'rightSide':null}"));

    }

    @Test
    public void updateRightSideWithoutValidDataExpectBadRequest() throws Exception {
        mvc.perform(post("/v1/diff/123/right")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateRightSideWithValidDataExpectCreated() throws Exception {
        Long comparisionId = 123L;
        String rightSide = "YmFzaWM=";

        Base64Comparison comparision = new Base64Comparison(comparisionId);
        comparision.setRightSide(rightSide);

        Mockito.when(comparisonService.updateRightSide(comparisionId, rightSide))
                .thenReturn(comparision);

        mvc.perform(post("/v1/diff/" + comparisionId + "/right")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"data\":\"" + rightSide + "\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{ 'id':" + comparisionId + ", 'leftSide':null, 'rightSide':'" + rightSide + "'}"));
    }

    @Test
    public void testGetDiff() throws Exception {
        Long comparisionId = 123L;
        String leftSide = "YmFzaWM=";
        String rightSide = "YmFzaWM=";

        Base64Comparison comparision = new Base64Comparison(comparisionId);
        comparision.setLeftSide(leftSide);
        comparision.setRightSide(rightSide);

        Mockito.when(comparisonService.getComparision(comparisionId)).thenReturn(comparision);
        Mockito.when(diffService.getDiff(leftSide, rightSide)).thenReturn(new DiffResponseDTO(true, MATCHED));

        mvc.perform(get("/v1/diff/123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'isEqual': true, 'message': '" + MATCHED + "'}"));
    }
}
