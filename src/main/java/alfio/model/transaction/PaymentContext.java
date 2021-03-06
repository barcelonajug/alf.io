/**
 * This file is part of alf.io.
 *
 * alf.io is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alf.io is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alf.io.  If not, see <http://www.gnu.org/licenses/>.
 */
package alfio.model.transaction;

import alfio.model.Event;
import alfio.model.system.Configuration;
import alfio.model.system.Configuration.ConfigurationPathKey;
import alfio.model.system.ConfigurationKeys;

import java.util.function.Function;

public class PaymentContext {

    private final Event event;
    private final Function<ConfigurationKeys, ConfigurationPathKey> configurationProvider;


    public PaymentContext(Event event) {
        this(event, Configuration.from(event.getOrganizationId(), event.getId()));
    }

    public PaymentContext(Event event, Function<ConfigurationKeys, ConfigurationPathKey> configurationProvider) {
        this.event = event;
        this.configurationProvider = configurationProvider;
    }

    /**
     * The {@link Event} on which this configuration refers to
     * @return Event, or null
     */
    public Event getEvent() {
        return event;
    }

    public ConfigurationPathKey narrow(ConfigurationKeys key) {
        return configurationProvider.apply(key);
    }
}
