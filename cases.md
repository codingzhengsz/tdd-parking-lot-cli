### Tasking

#### 停车

- 验证车辆

  - input: Car{license: String}
  - output: CarStatusEnum {status: String}

- 生成车票

  - input: Car{license: String}
  - output: {ticket: String}

  

#### 取车

- 验证车票
  - input: {ticket: String}
  - output: TicketStatusEnum {status: String}
- 去取车
  - input: {ticket: String}
  - Car{license: String}



### 停车

- Case 1

given: a car a parking boy

when: park

then: ticket

- Case 2

given: 2 cars 1 parking bot

when: park

then: 2 ticket

- Case 3

given: a parked car

when: park

then: String: cannot park

- Case 4

given: null

when: park

then: String: cannot park





### 取车

- Case 1

given: a ticket

when: fetch

then: a car

- Case 2

given: Wrong ticket

when: fetch

then: null  // TODO

- Case 3

given: a used ticket

when: fetch

then: null // TODO





```
//    @Test//    void should_return_multiple_tickets_when_park_given_multiple_cars() {////    }////    @Test//    void should_return_cannot_park_when_park_given_a_parked_car() {////    }//
```