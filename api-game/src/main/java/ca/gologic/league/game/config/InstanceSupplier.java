package ca.gologic.league.game.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Flux;

public class InstanceSupplier implements ServiceInstanceListSupplier {

  private final String serviceId;

  private final String teamServiceUri1;
  private final String teamServiceUri2;

  public InstanceSupplier(String serviceId, String teamServiceUri1, String teamServiceUri2) {
    this.serviceId = serviceId;
    this.teamServiceUri1 = teamServiceUri1;
    this.teamServiceUri2 = teamServiceUri2;
  }
  @Override
  public String getServiceId() {
    return serviceId;
  }

  @Override
  public Flux<List<ServiceInstance>> get(Request request) {
    return ServiceInstanceListSupplier.super.get(request);
  }

  @Override
  public Flux<List<ServiceInstance>> get() {
    return Flux.just(Arrays
        .asList(new DefaultServiceInstance(serviceId + "1", serviceId, teamServiceUri1, 8080, false),
            new DefaultServiceInstance(serviceId + "2", serviceId, teamServiceUri2, 8080, false)));
  }
}
