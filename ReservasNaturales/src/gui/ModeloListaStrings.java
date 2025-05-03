package gui;
import aplicacion.Area;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
/**
 *
 * @author basesdatos
 */
public class ModeloListaStrings extends AbstractListModel<Area> {
    private List<Area> areas;

    public ModeloListaStrings() {
        this.areas = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return this.areas.size();
    }

    @Override
    public Area getElementAt(int i) {
        return areas.get(i); // Asumimos que Area tiene un toString() adecuado.
    }
    
    public void setElementos(List<Area> areas) {
        this.areas = areas;
        fireContentsChanged(this, 0, areas.size() - 1);
    }

    public List<Area> getAreas() {
        return this.areas;
    }

    public void addElement(Area area) {
        this.areas.add(area);
        fireIntervalAdded(this, areas.size() - 1, areas.size() - 1);
    }

    void removeElement(Area areaSeleccionada) {
    int index = this.areas.indexOf(areaSeleccionada);
    if (index != -1) {
        this.areas.remove(index);
        fireIntervalRemoved(this, index, index);
    }
    }
}
