package com.ryana.repository;

import java.util.HashMap;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.ryana.domain.Person;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class PersonRepository {
   
    private static final String PERSON_ID = "personId";
    private final DynamoDBMapper mapper;

    public Person save(final Person person) {
        mapper.save(person);
        return person;
    }

    public List<Person> findAll() {
        return mapper.scan(Person.class, new DynamoDBScanExpression());
    }

    public Person findByPersonId(final String personId) {
        return mapper.load(Person.class, personId);
    }

    public String delete(final Person person) {
        mapper.delete(person);
        return "person deleted !!!";
    }

    public String update(final Person person) {
        mapper.save(person, buildExpression(person));
        return "person updated ...";
    }

    private DynamoDBSaveExpression buildExpression(final Person person) {
        final var expectedMap = new HashMap<String, ExpectedAttributeValue>();
        expectedMap.put(PERSON_ID, new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        final var dynamoDBSaveExpression = new DynamoDBSaveExpression();
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }

}
