import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> output = model.output();
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        String result = "";
        for (String e : output) {
            result += e;
        }
        view.updateOutputDisplay(result);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        this.model.output().clear();
        //Couldn't figure out how to do it with Stacks
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAppendEvent(String input) {
        Stack<String> temp = this.model.output();
        temp.flip();
        temp.push(input);
        temp.flip();
        this.model.output().transferFrom(temp);

        this.model.setInput("");

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processUndoEvent() {
        Stack<String> result = this.model.output();
        result.flip();

        String popped = result.pop();
        result.flip();
        this.model.output().transferFrom(result);
        this.model.setInput(popped);
    }

}
