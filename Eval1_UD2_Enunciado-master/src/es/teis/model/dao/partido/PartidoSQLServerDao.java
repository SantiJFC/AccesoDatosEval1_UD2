package es.teis.model.dao.partido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import es.teis.data.exceptions.InstanceNotFoundException;
import es.teis.db.DBCPDataSourceFactory;
import es.teis.model.Partido;
import es.teis.model.dao.AbstractGenericDao;
import es.teis.model.dao.IGenericDao;

public class PartidoSQLServerDao extends AbstractGenericDao<Partido>
implements IPartidoDao {

	private DataSource dataSource;
	//IMPORTANTES LÍNEAS 
    public PartidoSQLServerDao() {
        this.dataSource = DBCPDataSourceFactory.getDataSource();
    }

	@Override
	public Partido create(Partido entity) {
		String query = "INSERT INTO Partido (nombre, porcentaje, numero_votos) VALUES (?, ?, ?)";

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			
			statement.setString(1, entity.getNombre());
			statement.setFloat(2, entity.getPorcentaje());
			statement.setInt(3, entity.getVotos());

			int affectedRows = statement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating partido failed, no rows affected.");
			}

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					long generatedId = generatedKeys.getLong(1);
					entity.setId(generatedId);
				} else {
					throw new SQLException("Creating partido failed, no ID obtained.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			// Handle the exception appropriately or throw a custom exception
		}

		return entity;
	}

	@Override
	public Partido read(int id) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Partido entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsByName(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean transferirVotos(String nombreOrigen, String nombreDestino, int cantidadVotos) {
	    Connection connection = null;
	    PreparedStatement updateOrigen = null;
	    PreparedStatement updateDestino = null;

	    try {
	        connection = dataSource.getConnection();
	        connection.setAutoCommit(false);

	        // Actualizar votos del partido origen
	        String sqlUpdateOrigen = "UPDATE Partido SET numero_votos = numero_votos - ? WHERE nombre = ?";
	        updateOrigen = connection.prepareStatement(sqlUpdateOrigen);
	        updateOrigen.setInt(1, cantidadVotos);
	        updateOrigen.setString(2, nombreOrigen);
	        updateOrigen.executeUpdate();

	        // Actualizar votos del partido destino
	        String sqlUpdateDestino = "UPDATE Partido SET numero_votos = numero_votos + ? WHERE nombre = ?";
	        updateDestino = connection.prepareStatement(sqlUpdateDestino);
	        updateDestino.setInt(1, cantidadVotos);
	        updateDestino.setString(2, nombreDestino);
	        updateDestino.executeUpdate();

	        connection.commit();
	        return true;
	    } catch (SQLException e) {
	        if (connection != null) {
	            try {
	                connection.rollback();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (updateOrigen != null) {
	            try {
	                updateOrigen.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (updateDestino != null) {
	            try {
	                updateDestino.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.setAutoCommit(true);
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

}
