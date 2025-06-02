package com.example.demo.mail;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PedidoDTO;
import com.example.demo.dto.PedidoProdutoDTO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class NotaFiscalConfig {

    public void converterPdfArquivo(PedidoDTO pedido, String caminhoArquivo) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(caminhoArquivo)) {
            gerarPdf(pedido, fos);
        }
    }

    public byte[] converterPdfJson(PedidoDTO pedido) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        gerarPdf(pedido, baos);
        return baos.toByteArray();
    }

    private void gerarPdf(PedidoDTO pedido, OutputStream out) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, out);
        document.open();

        Paragraph titulo = new Paragraph("Nota Fiscal");
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" "));

        document.add(new Paragraph("Cliente: " + pedido.getCliente()));
        document.add(new Paragraph("Data do Pedido: " + pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        document.add(new Paragraph("Status: " + pedido.getStatus()));
        document.add(new Paragraph(" "));

        PdfPTable tabela = new PdfPTable(4);
        tabela.setWidthPercentage(100);
        tabela.addCell("Produto");
        tabela.addCell("Quantidade");
        tabela.addCell("Valor");
        tabela.addCell("Desconto");

        for (PedidoProdutoDTO item : pedido.getItens()) {
            tabela.addCell(item.getProduto());
            tabela.addCell(item.getQuantidade().toString());
            tabela.addCell(String.format("R$ %.2f", item.getValorVenda()));
            tabela.addCell(String.format("R$ %.2f", item.getDesconto()));
        }

        document.add(tabela);
        document.add(new Paragraph("\nTotal a Pagar: R$ " + String.format("%.2f", pedido.getValorTotal())));

        document.close();
    }
}
