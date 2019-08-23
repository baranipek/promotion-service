package com.promotion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"name", "unitPrice"})
@RequiredArgsConstructor
public class Item implements Serializable {
    private static final long serialVersionUID = -6029549449370814840L;
    private final String itemCode;
    private String name;
    private double unitPrice;
}



