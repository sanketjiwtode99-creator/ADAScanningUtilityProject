Feature: verify the Get Posts in API

  Scenario Outline: GET all posts from API
    Given Get call to "<url>"
    Then Response is "<statusCode>"

    Examples:
    | url   | statusCode |
    | /post | 200        |

  Scenario Outline: GET call to Single id
    Given Get call to "<url>"
    Then Response author is "<author>"

    Examples:
      | url   | author |
      | /post | user   |

