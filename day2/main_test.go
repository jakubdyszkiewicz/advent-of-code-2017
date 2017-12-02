package main

import "testing"

func TestSampleChecksum(t *testing.T) {
    data := [][]int{
        {5, 1, 9, 5},
        {7, 5, 3},
        {2, 4, 6, 8},
    }

    result := Checksum(data)
    expected := 18

    if result != expected {
        t.Errorf("Expected %v, got %v", expected, result)
    }
}

func TestSampleDivisableChecksum(t *testing.T) {
    data := [][]int{
        {5, 9, 2, 8},
        {9, 4, 7, 3},
        {3, 8, 6, 5},
    }

    result := ChecksumDivisable(data)
    expected := 9

    if result != expected {
        t.Errorf("Expected %v, got %v", expected, result)
    }
}
