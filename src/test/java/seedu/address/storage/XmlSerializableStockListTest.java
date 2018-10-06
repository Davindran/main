package seedu.address.storage;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.XmlUtil;
import seedu.address.model.StockList;
import seedu.address.testutil.TypicalItems;

public class XmlSerializableStockListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "XmlSerializableItemListTest");
    private static final Path TYPICAL_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalItemsStockList.xml");
    private static final Path INVALID_ITEM_FILE = TEST_DATA_FOLDER.resolve("invalidItemsStockList.xml");
    private static final Path DUPLICATE_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateItemsStockList.xml");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        XmlSerializableStockList dataFromFile = XmlUtil.getDataFromFile(TYPICAL_ITEMS_FILE,
                XmlSerializableStockList.class);
        StockList addressBookFromFile = dataFromFile.toModelType();
        StockList typicalItemsStockList = TypicalItems.getTypicalStockList();
        assertEquals(addressBookFromFile, typicalItemsStockList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        XmlSerializableStockList dataFromFile = XmlUtil.getDataFromFile(INVALID_ITEM_FILE,
                XmlSerializableStockList.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        XmlSerializableStockList dataFromFile = XmlUtil.getDataFromFile(DUPLICATE_ITEM_FILE,
                XmlSerializableStockList.class);
        thrown.expect(IllegalValueException.class);
        thrown.expectMessage(XmlSerializableStockList.MESSAGE_DUPLICATE_ITEM);
        dataFromFile.toModelType();
    }

}
