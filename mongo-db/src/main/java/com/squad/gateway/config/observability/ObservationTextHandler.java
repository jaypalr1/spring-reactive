package com.squad.gateway.config.observability;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
//@Component
public class ObservationTextHandler implements ObservationHandler<Context> {

  @Override
  public void onStart(Observation.Context context) {
    log.info("Before running the observation for context [{}], userType [{}]", context.getName(),
        getUserTypeFromContext(context));
  }

  @Override
  public void onStop(Observation.Context context) {
    log.info("After running the observation for context [{}], userType [{}]", context.getName(),
        getUserTypeFromContext(context));
  }

  @Override
  public boolean supportsContext(Observation.Context context) {
    return true;
  }

  private String getUserTypeFromContext(Observation.Context context) {
    return StreamSupport.stream(context.getLowCardinalityKeyValues().spliterator(), false)
        .filter(keyValue -> "userType".equals(keyValue.getKey()))
        .map(KeyValue::getValue)
        .findFirst()
        .orElse("UNKNOWN");
  }
}