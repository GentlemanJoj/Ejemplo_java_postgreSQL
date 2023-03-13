/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejemplo_java_postgresql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolás
 */
public class Cestudiantes {

    Integer id;
    String nombres;
    String apellidos;
    String fecha_nacimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void mostrarEstudiantes(JTable paramTablaTotalAlumnos) {
        Cconexion objetoConexion = new Cconexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Fecha Nacimiento");

        paramTablaTotalAlumnos.setModel(modelo);

        sql = "SELECT * FROM estudiantes;";

        String[] datos = new String[4];
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                modelo.addRow(datos);
            }

            paramTablaTotalAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    public void insertarEstudiantes(JTextField paramNombres, JTextField paramApellidos, JTextField paramFecha_Nacimiento) {
        setNombres(paramNombres.getText());
        setApellidos(paramApellidos.getText());
        setFecha_nacimiento(paramFecha_Nacimiento.getText());

        Cconexion objetoConexion = new Cconexion();

        String consulta = "insert into estudiantes (nombres, apellidos, fecha_nacimiento) values (?, ?, ?);";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombres());
            cs.setString(2, getApellidos());
            cs.setString(3, getFecha_nacimiento());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Insert correcto");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

    }

    public void seleccionarEstudiante(JTable paramTablaEstudiante, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramFecha_Nacimiento) {

        try {
            int fila = paramTablaEstudiante.getSelectedRow();

            if (fila >= 0) {
                paramId.setText(paramTablaEstudiante.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaEstudiante.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaEstudiante.getValueAt(fila, 2).toString());
                paramFecha_Nacimiento.setText(paramTablaEstudiante.getValueAt(fila, 3).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada :/");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }

    public void modificarEstudiantes(JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JTextField paramFecha_Nacimiento) {
        setId(Integer.parseInt(paramId.getText()));
        setNombres(paramNombres.getText());
        setApellidos(paramApellidos.getText());
        setFecha_nacimiento(paramFecha_Nacimiento.getText());

        Cconexion objetoConexion = new Cconexion();

        String consulta = "update estudiantes set nombres= ?, apellidos= ?, fecha_nacimiento=? where estudiantes.id=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, getNombres());
            cs.setString(2, getApellidos());
            cs.setString(3, getFecha_nacimiento());
            cs.setInt(4, getId());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Update correcto");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

    }

    public void eliminarEstudiante(JTextField paramId) {
        setId(Integer.parseInt(paramId.getText()));

        Cconexion objetoConexion = new Cconexion();

        String consulta = "delete from estudiantes where estudiantes.id=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1, getId());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Delete correcto");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }

    }
}
