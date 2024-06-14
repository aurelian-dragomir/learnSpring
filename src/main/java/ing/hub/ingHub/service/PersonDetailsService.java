package ing.hub.ingHub.service;

import ing.hub.ingHub.model.PersonDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static ing.hub.ingHub.utils.KafkaTopics.IN_TOPIC;

@Service
public class PersonDetailsService {

    @Autowired
    @Qualifier("personDetailsKafkaTemplate")
    private KafkaTemplate<Void, PersonDetailsDto> personDetailsKafkaTemplate;

    @Transactional
    public void send(PersonDetailsDto personDetails) {
        personDetailsKafkaTemplate.send(IN_TOPIC, personDetails);
    }
}
