package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
	@Id @GeneratedValue
	private Integer codigo;
	private LocalDate data;

	@OneToMany(mappedBy="pedido", cascade=CascadeType.ALL, orphanRemoval=true,
			fetch=FetchType.EAGER)
	private List<PedidoItem> itens = new ArrayList<>();
	
	public Pedido() {
		data = LocalDate.now();
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}
	
	public void addItem(PedidoItem pedidoItem) {
		if (itens == null) {
			itens = new ArrayList<>();
		}
		pedidoItem.setPedido(this);
		itens.add(pedidoItem);
	}
	
	public void removerItem(int index) {
		if (itens != null && itens.size() > index) {
			itens.remove(index);
		}
	}
	
	public Double getTotal() {
		Double total = 0.0;
		if (itens != null) {
			for (PedidoItem pedidoItem : itens) {
				total += pedidoItem.getTotalUnitario();
			}
		}
		return total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
