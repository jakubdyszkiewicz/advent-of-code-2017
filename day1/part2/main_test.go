package main

import "testing"

func TestSample1(t *testing.T) {
    result := AdjacentSum("1212")
    expected := 6
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample2(t *testing.T) {
    result := AdjacentSum("1221")
    expected := 0
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample3(t *testing.T) {
    result := AdjacentSum("123425")
    expected := 4
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample4(t *testing.T) {
    result := AdjacentSum("123123")
    expected := 12
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

func TestSample5(t *testing.T) {
    result := AdjacentSum("12131415")
    expected := 4
    if result != expected {
        t.Errorf("Expected is %v, but result was %v", expected, result)
    }
}

