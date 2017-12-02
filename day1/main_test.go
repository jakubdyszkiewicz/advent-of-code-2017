package main

import "testing"

func TestSample1(t *testing.T) {
    result := AdjacentSum("1122")
    expected := 3
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample2(t *testing.T) {
    result := AdjacentSum("1111")
    expected := 4
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample3(t *testing.T) {
    result := AdjacentSum("1234")
    expected := 0
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample4(t *testing.T) {
    result := AdjacentSum("91212129")
    expected := 9
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func Test_Should_Circular_Left(t *testing.T) {
    result := AdjacentSum("1121")
    expected := 2
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func Test_Should_Circular_Right(t *testing.T) {
    result := AdjacentSum("1211")
    expected := 2
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}
