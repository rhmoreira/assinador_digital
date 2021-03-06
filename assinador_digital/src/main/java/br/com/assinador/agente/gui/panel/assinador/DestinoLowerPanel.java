package br.com.assinador.agente.gui.panel.assinador;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.assinador.agente.gui.panel.assinador.listener.SelecaoDiretorioDestinoActionListener;
import br.com.mvp.view.annotation.Component;
import br.com.mvp.view.annotation.View;

@View
class DestinoLowerPanel extends JPanel {

	private static final long serialVersionUID = -8885728689572730475L;
	@Component
	private JTextField txtDestino;

	public DestinoLowerPanel() {
		
		JButton btnSelecionarDestino = new JButton("Destino");
		btnSelecionarDestino.setIcon(new ImageIcon(DestinoLowerPanel.class.getResource("/br/com/assinador/agente/gui/icon/folder.png")));
		
		txtDestino = new JTextField();
		txtDestino.setBackground(Color.WHITE);
		txtDestino.setEditable(false);
		txtDestino.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSelecionarDestino, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDestino, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtDestino, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
						.addComponent(btnSelecionarDestino, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		setLayout(groupLayout);
	
		btnSelecionarDestino.addActionListener(new SelecaoDiretorioDestinoActionListener(txtDestino));
	}
}
