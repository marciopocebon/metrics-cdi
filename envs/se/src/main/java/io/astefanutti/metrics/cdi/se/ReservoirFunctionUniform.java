/**
 * Copyright © 2013 Antonin Stefanutti (antonin.stefanutti@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.astefanutti.metrics.cdi.se;

import com.codahale.metrics.UniformReservoir;
import io.astefanutti.metrics.cdi.MetricsConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ReservoirFunctionUniform {

    AtomicInteger counter = new AtomicInteger();

    public int calls() {
        return counter.get();
    }

    void configuration(@Observes MetricsConfiguration configuration) {
        configuration.reservoirFunction((name, type) -> {
            counter.incrementAndGet();
            return Optional.of(new UniformReservoir());
        });
    }
}
