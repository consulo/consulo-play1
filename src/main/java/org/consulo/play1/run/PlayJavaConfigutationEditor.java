/*
 * Copyright 2013 Consulo.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.consulo.play1.run;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.roots.ui.configuration.ModulesCombobox;

/**
 * @author VISTALL
 * @since 22:07/17.03.13
 */
public class PlayJavaConfigutationEditor extends SettingsEditor<PlayJavaModuleBasedConfiguration>
{
	private JPanel rootPanel;
	private ModulesCombobox moduleComboBox;
	private JTextField programParametersField;

	public PlayJavaConfigutationEditor()
	{
	}

	@Override
	protected void resetEditorFrom(PlayJavaModuleBasedConfiguration s)
	{
		programParametersField.setText(s.programParameters);
		moduleComboBox.fillModules(s.getProject());
		moduleComboBox.setSelectedModule(s.getConfigurationModule().getModule());
	}

	@Override
	protected void applyEditorTo(PlayJavaModuleBasedConfiguration s) throws ConfigurationException
	{
		s.getConfigurationModule().setModule(moduleComboBox.getSelectedModule());
		s.programParameters = programParametersField.getText();
	}

	@NotNull
	@Override
	protected JComponent createEditor()
	{
		return rootPanel;
	}

}
