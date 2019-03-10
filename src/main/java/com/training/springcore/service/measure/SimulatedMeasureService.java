package com.training.springcore.service.measure;

import com.training.springcore.model.Captor;
import com.training.springcore.model.Measure;
import com.training.springcore.model.MeasureStep;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service("simuMeasure")
public class SimulatedMeasureService implements MeasureService {
    @Override
    public List<Measure> readMeasures(Captor captor, Instant start, Instant end, MeasureStep step) {
        checkReadMeasuresAgrs(captor, start, end, step);
        List<Measure> measures = new ArrayList<>();
        Instant current = start;
        while (current.isBefore(end)) {
            measures.add(new Measure(current, 12_000_000, captor));
            current = current.plusSeconds(step.getDurationInSecondes());
        }
        return measures;
    }
}
