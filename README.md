# Lunch Vote

A simple application to allow team members to vote for their restaurant of choice for lunch.

How to use:
1. Each team member submits the names of their preferred restaurants.
2. Once all members have submitted their choices, they can opt to receive a random restaurant selected from one of their preferred choices.
3. After lunch, team members can clear their previously voted restaurants.


APIs Provided:

- `POST /v1/restaurants` : add restaurant to list
- `GET /v1/restaurants` : get random restaurant name from list
- `DELETE /v1/restaurants` : remove all restaurant names from list
