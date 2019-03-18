package com.training.spring.bigcorp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.spring.bigcorp.controller.dto.MeasureDto;
import com.training.spring.bigcorp.model.*;
import com.training.spring.bigcorp.service.measure.SimulatedMeasureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@RestClientTest(SimulatedMeasureService.class)
public class SimulatedMeasureServiceTest {

    @Autowired
    private SimulatedMeasureService service;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private ObjectMapper objectMapper;

    SimulatedCaptor captor = new SimulatedCaptor("test",new Site("site"),500000,1000000);


    @Test
    public void readMeasures() throws Exception {
        Instant start = Instant.parse("2018-09-01T22:00:00Z");
        Instant end = Instant.parse("2018-09-01T22:30:00Z");
        List<MeasureDto> expectedMeasures = Arrays.asList(
                new MeasureDto(Instant.parse("2018-09-01T22:00:00Z"), 1234),
                new MeasureDto(Instant.parse("2018-09-01T22:30:00Z"), 4567)
        );
        String expectedJson = objectMapper.writeValueAsString(expectedMeasures);
        String request = "http://localhost:8090/measures?start=2018-09-01T22:00:00Z&" +
                "end=2018-09-01T22:30:00Z&min=500000&max=1000000&step=3600";
        this.server.expect(MockRestRequestMatchers.requestTo(request))
                .andRespond(MockRestResponseCreators.withSuccess(expectedJson,
                        MediaType.APPLICATION_JSON));
        List<Measure> measures = service.readMeasures(captor, start, end,
                MeasureStep.ONE_HOUR);

        assertThat(measures).hasSize(2);
// And we have a value for each hour of the period
        assertThat(measures)
                .extracting("instant", "valueInWatt")
                .containsExactly(
                        tuple(Instant.parse("2018-09-01T22:00:00Z"), 1234),
                        tuple(Instant.parse("2018-09-01T22:30:00Z"), 4567)
                );
    }
}
