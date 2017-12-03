package main

import "testing"

func Test_should_find_first_after_example(t *testing.T) {
    result := FirstAfter(330)
    expected := 351
    if result != expected {
        t.Errorf("Expected %v, got %v", expected, result)
    }
}
