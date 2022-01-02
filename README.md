# Cab Management Portal
[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)
[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

## _Problem Definition_
You have to build an inter city cab management portal to be used as an admin and booking tool.
This portal should be able to :
1. Register cabs.
2. Onboard various cities where cab services are provided.
3. Change current city (location) of any cab.
4. Change state of any cab. For this you will have to define a state machine for the cab ex: a cab must have at least these two basic states; IDLE and ON_TRIP
5. Book cabs based on their availability at a certain location. In case more than one cab are available , use the following strategy:
    - Find out which cab has remained idle the most and assign it.
    - In case of clash above, randomly assign any cab Assumption : a cab once assigned a trip cannot cancel/reject it

## Other Details
_Input:_ a snapshot of all cabs with their metadata and location
`List of <Cab_Id, Cab_State, City_Id>`
In case the Cab_State is ON_TRIP, the City_Id will be indeterminate

## Bonus
1. Provide insights such as for how much time was a cab idle in a given duration ?
2. Keep a record of the cab history of each cab. (A cab history could just be a record of what all states a cab has gone through)
3. Find cities where the demand for cabs is high and the time when the demand is highest

## Expectations
1. Clean functionally correct code.
2. Code should be extensible and unit testable.
3. It should be properly designed.
4. Don’t create any interactive UI.

