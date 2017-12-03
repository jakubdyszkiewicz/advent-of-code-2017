package main

import "fmt"

func generateNextEdges(data [][]int) [][]int {
    res := copyArrayExtendingEdges(data, len(data) + 2)
    edge := len(res)
    for i := edge - 2; i >= 0; i-- {
        res[i][edge - 1] = sumOfAdjacent(res, i, edge - 1)
    }
    for i := edge - 2; i >= 0; i-- {
        res[0][i] = sumOfAdjacent(res, 0, i)
    }
    for i := 1; i < edge - 1; i++ {
        res[i][0] = sumOfAdjacent(res, i, 0)
    }
    for i := 0; i < edge; i++ {
        res[edge - 1][i] = sumOfAdjacent(res, edge - 1, i)
    }
    return res
}

func copyArrayExtendingEdges(data [][]int, dim int) [][]int {
    res := make([][]int, dim)
    for i := range res {
        res[i] = make([]int, dim)
    }
    for i := range data {
        for j := range data[i] {
            res[i + 1][j + 1] = data[i][j]
        }
    }
    return res
}

func sumOfAdjacent(data [][]int, x int, y int) int {
    sum := 0
    diffs := []int{-1, 0, 1}
    edge := len(data)
    for _, xDiff := range diffs {
        for _, yDiff := range diffs {
            if x + xDiff >= 0 && x + xDiff < edge && y + yDiff >= 0 && y + yDiff < edge {
                sum += data[x + xDiff][y + yDiff]
            }
        }
    }
    return sum
}

func findFirstAfter(data [][]int, num int) int {
    edge := len(data)
    for i := edge - 2; i >= 0; i-- {
        if data[i][edge - 1] > num {
            return data[i][edge - 1]
        }
    }
    for i := edge - 1; i >= 0; i-- {
        if data[0][i] > num {
            return data[0][i]
        }
    }
    for i := 0; i < edge; i++ {
        if data[i][0] > num {
            return data[i][0]
        }
    }
    for i := 0; i < edge; i++ {
        if data[edge -1][i] > num {
            return data[edge - 1][i]
        }
    }

    return -1
}

func FirstAfter(num int) int {
    data := [][]int{{1}}
    res := -1
    for ;res == -1; {
        data = generateNextEdges(data)
        res = findFirstAfter(data, num)
    }
    return res
}

func main() {
    fmt.Print(FirstAfter(368078))
}
