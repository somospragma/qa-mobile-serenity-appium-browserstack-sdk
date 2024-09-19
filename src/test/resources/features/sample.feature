Feature: Form

  Rule the form is autocomplete

    @Example1_with_autocomplete
    Example: autocomplete with the first option after typing the letter a
      Given the user is in Inputs Screen
      When fill out the name with the first suggest with a
    # Then ...

    @Example2_with_autocomplete
    Example: autocomplete with the first option after typing the letter a
      Given the user is in Inputs Screen
      When fill out the name with the first suggest with b
    # Then ...
