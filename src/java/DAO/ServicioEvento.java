package DAO;

import Conexion.DbUtil;
import VO.Evento;
import VO.Persona;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class ServicioEvento {

    private Connection connection;

    public ServicioEvento() {
        connection = DbUtil.getConnection();
    }

    public void agregarEvento(Evento SP) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into evento(id_evento,id_persona,nombre_eve,dia,hora_ini,hora_fin) values (?,?,?,?,?,? )");
            // Parameters start with 1
            preparedStatement.setInt(1, SP.getId_evento());
            preparedStatement.setInt(2, SP.getId_persona());
            preparedStatement.setString(3, SP.getNombre_eve());
            preparedStatement.setString(4, SP.getDia());
            preparedStatement.setInt(5, SP.getHora_ini());
            preparedStatement.setInt(6, SP.getHora_fin());
            

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Evento> listarE() {
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select * from evento");
            while (rs.next()) {
                Evento P = new Evento();

                P.setId_evento(rs.getInt("id_evento"));
                P.setId_persona(rs.getInt("id_persona"));
                P.setNombre_eve(rs.getString("nombre_eve"));
                P.setDia(rs.getString("dia"));
                P.setHora_ini(rs.getInt("hora_ini"));
                P.setHora_fin(rs.getInt("hora_fin"));
                

                

                eventos.add(P);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

}
